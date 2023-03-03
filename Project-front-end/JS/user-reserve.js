//Drop-down Account
    let subMenu = document.getElementById("subMenu");

    function toggleMenu(){
        subMenu.classList.toggle("open-menu");
    }


// On click Button event Code
$('#save').on('click', function() {
    var name = $('#name').val();
    var email = $('#email').val();
    var tel = $('#tel').val();
    var datetimeLocal = $('#datetimeLocal').val();
    var numberg = $('#numberg').val();
    var numbert = $('#numbert').val();
    var payment = $('#payment').val();
    var count = $('#myData tr').length;

    if(name !="" && email !="" && tel !="" && datetimeLocal !="" && numberg !="" && numbert !=""){
        $('#myData tbody').append('<tr class="child"><td>'+count+'</td><td>'+name+'</td><td>'+email+'</td><td>'+tel+'</td><td>'+datetimeLocal+'</td><td>'+numberg+'</td><td>'+numbert+'</td><td>'+payment+'</td><td><a href="javascript:void(0);" class="remdata"><i class="fa fa-trash"></i></a></td></tr>');
    }
    });

    // On click delete event Code
    $(document).on('click','.remdata',function(){
        $(this).parent().parent().remove();
            $('#myData tbody tr').each(function(i){            
            $($(this).find('td')[0]).html(i+1);          
        });
    });
