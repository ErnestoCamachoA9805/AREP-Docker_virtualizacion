var puertos= [35001,35002,35003];
var flag= 0;
const init = ()=>{
    document.getElementById("formulario").addEventListener('submit',(event)=>{
        event.preventDefault()
        let puerto= puertos[flag];
	    flag = flag+1>2 ? 0:flag+1;
        let text= document.getElementById("stringToSend").value;
        fetch(`http://ec2-34-227-27-85.compute-1.amazonaws.com:${puerto}/logservice?cadena=${text}`)
        .then(data=>data.json())
        .then(data=>{
            let result=""
            data.forEach(element => {
                result+=`<h1>${element.data} ${element.date.$date}</h1>`
            });
            document.getElementById("logs").innerHTML=result
        })
    })
}

init()