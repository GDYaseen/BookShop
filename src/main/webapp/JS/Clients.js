let ajax = new XMLHttpRequest()
let link = window.location.pathname
let currentUserId

function addClient(){
    let nom=document.querySelector("#nameAdd").value
    let email=document.querySelector("#emailAdd").value

    if(nom=="" || email==""){
        alert("Remplir tous les valeurs avant d'ajouter!")
        return;
    }

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        alert("Le client a été ajouté.")
        window.location.reload()
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`type=add&name=${nom}&email=${email}`)
}

function showDetails(id,email,nom){
	
    document.querySelector("#emailAndDelete>h4").innerHTML="Email: "+email;
    document.querySelector("#emailAndDelete>button").removeAttribute("hidden")
    document.querySelector("#emailAndDelete>button").setAttribute("onClick", "deleteUser(currentUserId)")
    document.querySelector("#modifyButton").removeAttribute("hidden")
    document.querySelector("#bookAdd").innerHTML=`
    				<input id="addSale" type="number" name="codeSerial" placeholder="Code du livre"/>
                    <button onClick="addSale()">Ajouter</button>`
                    
    currentUserId=id
    let tbody=""
    document.querySelector("#nameAdd").value=nom
    document.querySelector("#emailAdd").value = email

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        let s = ajax.responseText.substring(1)
        let list = s.substring(0,s.length-1).split(",")
        for(let i in list){
            list[i] = list[i].replaceAll("#",",")
            if(list[i]!=""){
	            list[i] = JSON.parse(list[i])
			}else{
                list=[]
                break;
            }
        }
    
    let table = document.querySelector("#achats>div>table")
    tbody=`<tr><th>Code</th><th>Livre</th><th>Auteur</th><th>Date d'achat</th><th>Prix</th><th></th></tr>`
    for(let o of list){
        tbody+=`
            <tr>
                <td>${o["Id"]}</td>
                <td>${o["Titre"]}</td>
                <td>${o["Auteur"]}</td>
                <td>${o["Date_vente"]}</td>
                <td>${o["Prix"]}</td>
                <td><button class="deleteSale" onClick="deleteSale(${o["Id"]},${id},'${email}','${o["Date_vente"]}','${nom}')">-</button></td>
            </tr>
        `
    }
    table.innerHTML=tbody
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`type=details&id=${id}`)
}

function deleteSale(id_livre,id_client,email,date,nom){
    ajax.open("POST", link)
    ajax.onload = (e)=>{
        showDetails(id_client, email,nom)
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`type=deleteSale&id=${id_livre}&id_client=${id_client}&date=${date}`)
}
function deleteUser(id){
    ajax.open("POST", link)
    ajax.onload = (e)=>{
        alert("Client supprimé")
        window.location.reload()
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`type=deleteClient&id_client=${id}`)
}
function modifyClient(){
    let nom=document.querySelector("#nameAdd").value
    let email=document.querySelector("#emailAdd").value

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        alert("Client modifié")
        window.location.reload()
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`type=modifyClient&id_client=${currentUserId}&name=${nom}&email=${email}`)
}
function addSale(){
    let id_livre= document.querySelector("#addSale").value

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        if(ajax.responseText=="Success"){
            alert("Achat ajouté")
            window.location.reload()
        }else{
            alert("Ce livre n'existe pas")
        }
    }

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    let d = new Date()
    ajax.send(`type=addSale&id_client=${currentUserId}&id=${id_livre}&date=${d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()}`)
}
// "{\"Id_client\":"+getId_client()+",\"Id_livre\":"+getId_livre()+",\"Date_vente\":"+getDate_vente()+",\"Id\":"+getId()+",\"Sold\":"+getSold()+",\"Titre\":"+getTitre()+",\"Auteur\":"+getAuteur()+",\"Categories\":"+getCategories()+",\"Prix\":"+getPrix()+",\"Total\":"+getTotal(+")}"
