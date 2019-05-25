$(document).ready(function () {
    $('table').tablesort();
});
var filtro = document.getElementById('busca');
var tabela = document.getElementById('table');
filtro.onkeyup = function () {
    var nomeFiltro = filtro.value;
    for (var i = 1; i < tabela.rows.length; i++) {
        var conteudoCelula = tabela.rows[i].cells[0].innerText;
        var corresponde = conteudoCelula.toLowerCase().indexOf(nomeFiltro) >= 0;
        tabela.rows[i].style.display = corresponde ? '' : 'none';
    }
};