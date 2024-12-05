function pesquisa() {
	var inputValue = $('#search').val();
	var type = $('#type').val();
	var pageOffset = parseInt($('#pageOffset').val())-1;
	console.log(type);

    $.ajax({
        url: 'http://localhost:8080/v1/metadata/search?page='+pageOffset,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ search: inputValue, type: type }),
        success: function (response) {
            createResponse(response);
        },
        error: function (xhr, status, error) {
            alert(`<p>Erro: ${xhr.responseText || status}</p>`);
        }
    });
}

function createResponse(response) {
	$('#table-response').empty();
	var html = "";

	response.content.forEach(line => {
		html+="<tr>";
		html+="<td>"+line['type']+"</td>";
		html+="<td>"+line['databaseName']+"</td>";
		html+="<td>"+line['schema']+"</td>";
		html+="<td>"+line['tableName']+"</td>";
		html+="<td>"+line['columnName']+"</td>";
		html+="<td>"+line['indTechnology']+"</td>";
		html+="</tr>";
	})

	$('#table-response').append(html);
}

function proximo() {
	var pageOffset = parseInt($('#pageOffset').val()) + 1;
	$('#pageOffset').val(pageOffset);
	pesquisa();
}

function anterior() {
	var pageOffset = parseInt($('#pageOffset').val()) - 1;
	if (pageOffset < 1) {
		pageOffset = 1;
	}
	$('#pageOffset').val(pageOffset);
	pesquisa();
}