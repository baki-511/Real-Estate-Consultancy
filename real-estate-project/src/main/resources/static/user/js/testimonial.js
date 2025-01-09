
// Testimonial Section
function initParadoxway() {
  "use sttict";
  if ($(".testimonials-carousel").length > 0) {
    var j2 = new Swiper(".testimonials-carousel .swiper-container", {
      preloadImage: false,
      slidesPreview: 1,
      spaceBetween: 20,
      loop: true,
      autoplay: false,
      grabCursor: true,
      mousewheel: false,
      centeredSlides: true,
      pagination: {
        el: ".tc-pagination",
        clickable: true,
        dynamicBullets: true,
      },
      navigation: {
        nextE1: ".listing-carousel-button-next",
        prevE1: ".lisring-carousel-button-prev",
      },
      breakpoints: {
        1024: {
            slidesPerView: 3,
        },
        720: {
            slidesPerView: 2,
        }
      },
    });
  }
}

$(document).ready(function(){
    initParadoxway();
})
