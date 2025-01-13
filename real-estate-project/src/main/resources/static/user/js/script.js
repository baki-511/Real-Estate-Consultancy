/*=============== SHOW MENU ===============*/
const showMenu = (toggleId, navId) =>{
    const toggle = document.getElementById(toggleId),
          nav = document.getElementById(navId)

    toggle.addEventListener('click', () =>{
        // Add show-menu class to nav menu
        nav.classList.toggle('show-menu')

        // Add show-icon to show and hide the menu icon
        toggle.classList.toggle('show-icon')
    })
 }

 showMenu('nav-toggle','nav-menu')


// ================== Customer img slider =================

$('.logo-slider').slick({
    slidesToShow: 5,
    slidesToScroll: 1,
    // dots: true,
    arrows: true,
    autoplay: true,
    autoplaySpeed: 2000,
    infinite: true,
    responsive: [
        {
            breakpoint: 900,
            settings: {
                slidesToShow:3
            }
        },

        {
            breakpoint: 750,
            settings: {
                slidesToShow:2
            }
        },

        {
            breakpoint: 521,
            settings: {
                slidesToShow:1
            }
        },

    ]
})

  let toast = document.querySelector(".toast-msg");
  setTimeout(()=>{
             toast.remove();
         },3000)

