let ajax = new XMLHttpRequest()
let link = window.location.pathname
let currentSelectedUser


function addUser(){
    let username=document.querySelector("#username").value
    let password=document.querySelector("#password").value
    let admin=document.querySelector("#admin").checked

    if(username=="" || password==""){
        alert("Remplir tous les valeurs avant d'ajouter!")
        return;
    }

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        alert("L'utilisateur a été ajouté.")
        window.location.reload()
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`username=${username}&password=${password}&admin=${admin}`)
}

function toggleModify(id,username,password,admin){
    currentSelectedUser = id
    document.getElementById("addUser").style.display="none"
    document.getElementById("modifyUser").style.display="flex"
    document.querySelector("#username").value=username
    document.querySelector("#password").value=password
    document.querySelector("#admin").checked=(admin=="true"?true:false)
}

function modifyUser(id){
    let username=document.querySelector("#username").value
    let password=document.querySelector("#password").value
    let admin=document.querySelector("#admin").checked

    console.log(id,username,password,admin)
    ajax.open("PUT", link+`?id=${id}&username=${username}&password=${password}&admin=${admin}`)
    ajax.onload = (e)=>{
        alert("L'utilisateur a été modifié.")
        window.location.reload()
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send()
}
function deleteUser(id){
    ajax.open("DELETE", link+`?id=${id}`)
    ajax.onload = (e)=>{
        alert("L'utilisateur a été supprimé.")
        window.location.reload()
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send()
}