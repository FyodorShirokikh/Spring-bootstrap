/**
*
*/

$('document').ready(function() {

    $('.table .eBtn').on('click',function(event){
        event.preventDefault();
        var href= $(this).attr('href');
        $.get(href, function(user, status) {
            $('#id').val(user.id);
            $('#username').val(user.username);
            $('#lastname').val(user.lastname);
            $('#age').val(user.age);
            $('#email').val(user.email);
//            ($("#roles").val(user.roles) || []).join('; ');
            if (user.roles.length > 1) {
               $('#roles').val([ user.roles[0].name, user.roles[1].name ]);
            }
            else {
                  $('#roles').val(user.roles[0].name);
            };
        });
        $('#editModal').modal();
    });
    $('.table .dBtn').on('click',function(event){
        event.preventDefault();
        var href= $(this).attr('href');
        $.get(href, function(user, status) {
            $('#dID').val(user.id);
            $('#dFirstName').val(user.username);
            $('#dLastName').val(user.lastname);
            $('#dAge').val(user.age);
            $('#dEmail').val(user.email);
            if (user.roles.length > 1) {
               $('#dRoles').val([ user.roles[0].name, user.roles[1].name ]);
            }
            else {
                  $('#dRoles').val(user.roles[0].name);
            };
        });
        $('#deleteModal').modal();
    });
//    var output = ($("#roles").val() || []).join(', ');
    var values = $('.editRoles').map(function() {
//        alert(this.value);
        return this.value;
    });
//    $('select#roles').on('change', function(evt, params) {
//        alert(params.selected);
//        var currentSelection;
//        if (selectedOption) {
//          var currentValues = $(this).val();
//          currentSelection = currentValues.filter(function(el) {
//            return selectedOption.indexOf(el) < 0;
//          });
//        }
//        selectedOption = $(this).val();
//        $('#roles').text(currentSelection);
//        alert(currentSelection);
//    });
})