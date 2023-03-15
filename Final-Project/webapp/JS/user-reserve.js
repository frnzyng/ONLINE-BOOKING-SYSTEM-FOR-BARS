//Drop-down Account
    let subMenu = document.getElementById("subMenu");

    function toggleMenu(){
        subMenu.classList.toggle("open-menu");
    }


// On click Button event Code (Modified)
$(document).ready(function() {
    // Handle delete button clicks
    $(".delete-row").click(function() {
        // Get the id of the row to delete
        var ID = $(this).data("id");

        // Display a confirmation dialog
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this reservation!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
                // Submit a POST request to the servlet to delete the row
                $.post("CancelReservation", {ID: ID}, function() {
                    // Redirect to the reservation cancelled page
                    window.location.href = "ReservationCancelled.jsp";
                });
            } else {
                // Do nothing
            }
        });
    });
});
