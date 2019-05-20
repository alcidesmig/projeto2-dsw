package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.DAOPromocao;
import br.ufscar.dc.dsw.dao.DAOSalaDeTeatro;
import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.pojo.Promocao;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/promocao/*")
public class PromocaoController extends HttpServlet {

    private DAOPromocao daoProm;
    private DAOSalaDeTeatro daoSalaDeTeatro;
    private DAOSiteDeVenda daoSiteDeVenda;

    @Override
    public void init() {
        daoProm = new DAOPromocao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getRequestURI();
            action = action.split("/")[action.split("/").length - 1];

            switch (action) {
                case "cadastro":
                    insere(request, response);
                    break;
                case "lista":
                    lista(request, response);
                    break;
                case "edicao":
                    atualize(request, response);
                    break;
                case "gerenciar":
                    listaGerenciar(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PromocaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getRequestURI();
        action = action.split("/")[action.split("/").length - 1];
        try {
            switch (action) {
                case "cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "gerenciar":
                    listaGerenciar(request, response);
                    break;
                case "edicao_form":
                    apresentaFormEdicao(request, response);
                    break;
                case "remocao":
                    remove(request, response);
                    break;
                case "lista":
                    lista(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PromocaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        if (new AuthController().hasRole(request, "admin") || new AuthController().hasRole(request, "gerenciar_promocao")
                || new AuthController().hasRole(request, "listar_promocao")) {
            if (request.getMethod().equals("POST")) {
                List<Promocao> lista = daoProm.getByTeatro(daoSalaDeTeatro.getByNome(request.getParameter("busca")));
                request.setAttribute("listaPromocao", lista);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/lista.jsp");
                dispatcher.forward(request, response);
            } else {
                List<Promocao> lista = daoProm.getAll();
                request.setAttribute("listaPromocao", lista);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/lista.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/projeto1_dsw/403.jsp");
        }

    }

    private void listaGerenciar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        if (AuthController.hasRole(request, "admin") || AuthController.hasRole(request, "gerenciar_promocao")) {
            if (request.getParameter("busca") != null) {
                List<Promocao> lista = daoProm.getByNameAndUser(String.valueOf(request.getParameter("busca")), new DAOSalaDeTeatro().getByEmail(AuthController.getUser(request).getEmail()).get(0).getCnpj());
                request.setAttribute("listaPromocao", lista);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/gerenciar.jsp");
                dispatcher.forward(request, response);
            } else {
                List<Promocao> lista = null;
                if (AuthController.hasRole(request, "admin") || AuthController.hasRole(request, "gerenciar_promocao")) {
                    lista = daoProm.getAll();

                }

                request.setAttribute("listaPromocao", lista);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/gerenciar.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/projeto1_dsw/403.jsp");
        }
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaUrl", new DAOSiteDeVenda().getAll());
        request.setAttribute("listaSalas", new DAOSalaDeTeatro().getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.valueOf(request.getParameter("id"));
        Promocao prom = daoProm.get(id);

        request.setAttribute("prom", prom);
        request.setAttribute("listaUrl", new DAOSiteDeVenda().getAll());
        request.setAttribute("listaSalas", new DAOSalaDeTeatro().getAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException, NoSuchAlgorithmException {
        request.setCharacterEncoding("UTF-8");
        if (AuthController.hasRole(request, "admin") || AuthController.hasRole(request, "gerenciar_promocao")) {
            try {
                String endereco_url = request.getParameter("endereco_url");
                String nome_peca = request.getParameter("nome_peca");
                String datetime = request.getParameter("datetime");
                double preco = Double.parseDouble(request.getParameter("preco"));
                String cnpj = request.getParameter("cnpj_teatro");
                SalaDeTeatro sala = daoSalaDeTeatro.getByCnpj(cnpj);
                SiteDeVenda site = daoSiteDeVenda.getByEndereco(endereco_url);
                Promocao promocao = new Promocao(preco, datetime, site, sala, nome_peca);
                Boolean cadastra = true;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                for (Promocao p : daoProm.getByTeatro(sala)) {
                    if (p.getDatetime().equals(datetime)) {
                        cadastra = false;
                    }
                }
                if (!cadastra) {
                    request.setAttribute("listaSalas", new DAOSalaDeTeatro().getAll());
                    request.setAttribute("prom", promocao);
                    request.setAttribute("erro", "Promoção no mesmo horário!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/cadastro.jsp");
                    dispatcher.forward(request, response);
                    response.setStatus(500);
                } else {
                    daoProm.save(promocao);
                    response.sendRedirect("lista");
                }
            } catch (IOException | NumberFormatException | ServletException e) {
                request.setAttribute("erro", "Erro ao fazer o cadastro! Confira a integridade dos dados.");
                request.setAttribute("listaSalas", new DAOSalaDeTeatro().getAll());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/cadastro.jsp");
                dispatcher.forward(request, response);
                response.setStatus(500);
            }
        } else {
            response.sendRedirect("lista");
        }
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id_promocao = Integer.valueOf(request.getParameter("id_promocao"));
            String endereco_url = request.getParameter("endereco_url");
            String nome_peca = request.getParameter("nome_peca");
            String datetime = request.getParameter("datetime");
            double preco = Double.parseDouble(request.getParameter("preco"));
            String cnpj = request.getParameter("cnpj_teatro");
            SalaDeTeatro sala = daoSalaDeTeatro.getByCnpj(cnpj);
            SiteDeVenda site = daoSiteDeVenda.getByEndereco(endereco_url);
            Promocao promocao = new Promocao(id_promocao, preco, datetime, site, sala, nome_peca);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            sdf.parse(datetime);
            Boolean cadastra = true;

            for (Promocao p : daoProm.getByTeatro(sala)) {
                if (p.getDatetime().equals(datetime)) {
                    cadastra = false;
                }
            }
            if (!cadastra) {
                request.setAttribute("listaUrl", new DAOSiteDeVenda().getAll());
                request.setAttribute("listaSalas", new DAOSalaDeTeatro().getAll());
                request.setAttribute("prom", promocao);
                request.setAttribute("erro", "Promoção no mesmo horário!");
                request.setAttribute("editar", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/edicao.jsp");
                dispatcher.forward(request, response);
            } else {
                daoProm.update(promocao);
                response.sendRedirect("listaGerenciar");
            }
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao fazer o cadastro! Confira a integridade dos dados.");
            request.setAttribute("listaSalas", new DAOSalaDeTeatro().getAll());
            request.setAttribute("editar", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/templates_promocao/edicao.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        Promocao p = new Promocao(id);
        daoProm.delete(p);
        response.sendRedirect("gerenciar");
    }

}
