$(document).ready(function(){
	 $('#data-table-simple').DataTable({
	        info: false,
	        language : {
	            emptyTable : "Nenhum resultado encontrado",
	            search: "Buscar",
	            lengthMenu: "Tamanho da pagina _MENU_",
	            paginate: {
	                first: "Primeira",
	                last: "Ultima",
	                next: "Proxima",
	                previous: "Anterior"
	            },
	            decimal: ",",
	            thousands: "."
	        },
	        lengthMenu: [ [10, 25, 50, -1], [10, 25, 50, "Todos"]] ,
	        "pageLength": 25
	    });
    
    var table = $('#data-table-row-grouping').DataTable({
        "columnDefs": [
            { "visible": false, "targets": 2 }
        ],
        "order": [[ 2, 'asc' ]],
        "displayLength": 25,
        "drawCallback": function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;
 
            api.column(2, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="5">'+group+'</td></tr>'
                    );
 
                    last = group;
                }
            } );
        }
    });
 
    // Order by the grouping
    $('#data-table-row-grouping tbody').on( 'click', 'tr.group', function () {
        var currentOrder = table.order()[0];
        if ( currentOrder[0] === 2 && currentOrder[1] === 'asc' ) {
            table.order( [ 2, 'desc' ] ).draw();
        }
        else {
            table.order( [ 2, 'asc' ] ).draw();
        }
    } );


    });