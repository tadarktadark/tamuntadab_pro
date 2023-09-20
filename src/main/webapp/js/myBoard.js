$(document).ready(function() {
    $('#zoomInModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var reason = button.data('reason'); // Extract info from data-* attributes
        var modal = $(this);
        modal.find('.modal-body h5').text(reason);
    });
});