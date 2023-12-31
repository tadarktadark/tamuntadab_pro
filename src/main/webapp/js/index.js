$(document).ready(function(){
        $.ajax({
            url: './subjectsSlider.do',
            method: 'GET',
            success: function(data) {
                var source = $("#subjects-template").html();
                var template = Handlebars.compile(source);
                
                var html = template(data);
                
                $('#subjectTagSlider').append(html);
                
                $('#subjectTagSlider').slick({
                infinite : true,
                slidesToShow : 5,
                slidesToScroll : 1,
                autoplay : true,
                autoplaySpeed : 2000,
                arrows : false
            });
            },
            error: function(err) {
               console.error(err);
           }
       });
});