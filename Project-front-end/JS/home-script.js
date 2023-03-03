let valueDisplays = document.querySelectorAll(".num");
let interval =  1000;

valueDisplays.forEach((valueDisplay) => {
    let startValue = 0;
    let endValue = parseInt(valueDisplay.getAttribute("data-val"));
     
    let duration = Math.floor(interval / endValue);
    let counter = setInterval(function  ( ) {

        startValue +=1;
    
        if (endValue== 0) {
            startValue -= 1;
        }
        valueDisplay.textContent=startValue;
        if (startValue== endValue) {
            clearInterval(counter);
        }
     },duration);
})


/*$(document).ready(function(){
    $('.num').counterUp({
      delay: 10,
      time: 1200
    });
});*/


