$(document).ready(function () {
    $('table').tablesort();
});
var filtro = document.getElementById('busca');
var tabela = document.getElementById('table');
filtro.onkeyup = function () {
    var nomeFiltro = filtro.value;
    for (var i = 1; i < tabela.rows.length; i++) {
        var conteudoCelula1 = tabela.rows[i].cells[0].innerText;
        var conteudoCelula2 = tabela.rows[i].cells[3].innerText;
        var corresponde = conteudoCelula1.toLowerCase().indexOf(nomeFiltro) >= 0 || conteudoCelula2.toLowerCase().indexOf(nomeFiltro) >= 0;
        tabela.rows[i].style.display = corresponde ? '' : 'none';
    }
};