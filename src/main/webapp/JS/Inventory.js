let serialCode = document.querySelector("#search>input[name='serialCode']")
let Title = document.querySelector("#search>input[name='Title']")
let Auteur = document.querySelector("#search>input[name='Auteur']")
let category = document.querySelector("#search>select[name='category']")

let ajax = new XMLHttpRequest()
let link = window.location.pathname

function search(){
    let req = []
    if(serialCode.value!=""){ req.push(`id=${serialCode.value}`)}
    if(Title.value!=""){ req.push(`lower(titre) like lower("#${Title.value}#")`)}
    if(Auteur.value!=""){ req.push(`lower(auteur) like lower("#${Auteur.value}#")`)}
    if(category.value!=""){ req.push(`lower(categories) like lower("#${category.value}#")`)}

    let s = 1
    
    if(req.length>0) {
        s=req.join(" AND ")
    }
    ajax.open("POST", link)
    ajax.onload = (e)=>{
        reloadTable(ajax.responseText)
    }
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    ajax.send(`type=req&req=${s}`)
}
function reloadTable(data){
    let table = document.querySelector("#InventoryTable>table")
    let tbody =`<tr><th>Code</th><th>Titre</th><th>Auteur</th><th>Category</th><th>Prix</th><th></th></tr>`
        let s = data.substring(1)
        let list = s.substring(0,s.length-1).split(",")
        for(let i in list){
            list[i] = list[i].replaceAll("#",",")
    		if(data!="[{}]"){
				console.log(data)
            	list[i] = JSON.parse(list[i])
		    }else{
                list=[]
                break;
            }
        }
    
        for(let o of list){
            tbody+=`
            <tr><td id="selectedId">${o["Id"]}</td>
            <td id="selectedTitre">${o["Titre"]}</td>
            <td id="selectedAuteur">${o["Auteur"]}</td>
            <td id="selectedCategories">${o["Categories"]}</td>
            <td id="selectedPrix">${o["Prix"]}</td>
            <td id="selectedStock" hidden>${o["Stock"]}</td>
            <td id="selectedCover" hidden>${o["Cover"]}</td>
            <td><button onclick=\"selectBook(this)\">Selectionner</button></td>
            </tr>
            `
        }
        table.innerHTML=tbody
}

function addBook(){
    
    let serialCodeAdd = document.querySelector("#add>input[name='serialCode']").value
    let TitleAdd = document.querySelector("#add>input[name='Title']").value
    let AuteurAdd = document.querySelector("#add>input[name='Auteur']").value
    let categoryAdd = document.querySelector("#add>select[name='category']").value
    let prix = document.querySelector("#add>input[name='Prix']").value
    let coverAdd = document.querySelector("#add>input[name='cover']").files[0]
    let stockAdd = document.querySelector("#add>input[name='count']").value

    if(stockAdd=="" || serialCodeAdd=="" || TitleAdd=="" || AuteurAdd=="" || categoryAdd=="" || prix=="" || coverAdd==null){
        alert("Remplir tous les valeurs avant d'ajouter!")
        return;
    }

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        alert("Le livre a été ajouté.")
        window.location.reload()
    }
    
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    getBase64(coverAdd, function(base64String) {
        ajax.send(`type=add&stock=${stockAdd}&id=${serialCodeAdd}&Title=${TitleAdd}&Auteur=${AuteurAdd}&Categories=${categoryAdd}&Prix=${prix}&Cover=${base64String}`)
    });
}
function getBase64(file, callback){
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
        callback(reader.result.replaceAll("+","%2B"));
    };
    reader.onerror = function (error) {console.log('Error:'+ error);}
}
let currentSelection
function selectBook(e){
    currentSelection = e
    let selectedId= e.parentElement.parentElement.querySelector("#selectedId").innerHTML
    let selectedTitre= e.parentElement.parentElement.querySelector("#selectedTitre").innerHTML
    let selectedAuteur= e.parentElement.parentElement.querySelector("#selectedAuteur").innerHTML
    let selectedCategories= e.parentElement.parentElement.querySelector("#selectedCategories").innerHTML
    let selectedPrix= e.parentElement.parentElement.querySelector("#selectedPrix").innerHTML
    let selectedStock= e.parentElement.parentElement.querySelector("#selectedStock").innerHTML
    let selectedCover= e.parentElement.parentElement.querySelector("#selectedCover").innerHTML
    
    let bookDetails = document.querySelector("#bookDetails")
    bookDetails.innerHTML=`
        <img src="${selectedCover}" alt="" srcset="">
        <h1 id="bookTitle">${selectedTitre}</h1>
        <h4 id="bookAuteur">Auteur:${selectedAuteur}</h4>
        <h4 id="bookPrix">Prix:${selectedPrix}</h4>
        <h4 id="bookCategories">Categories:${selectedCategories}</h4>
        <h4 id="bookStock">Stock:${selectedStock}</h4>
        <div id="bookButtons">
            <button onClick="deleteBook(${selectedId})" id="delete">Supprimer</button>
            <button onClick="toggleModify('${selectedCover}','${selectedTitre}','${selectedAuteur}',${selectedPrix},'${selectedCategories}',${selectedStock},${selectedId})" id="toggle">Modifier</button>
        </div>
    `
}

function deleteBook(id){
    ajax.open("DELETE", link+`?id=${id}`)
    ajax.onload = (e)=>{
        reloadTable(ajax.responseText)
    }
    ajax.send()
}

function toggleModify(selectedCover,selectedTitre,selectedAuteur,selectedPrix,selectedCategories,selectedStock,selectedId){
    let catgs = document.querySelector("#categorySelect")
    let bookDetails = document.querySelector("#bookDetails")
    bookDetails.innerHTML=`
        <img src="${selectedCover}" alt="" srcset="">
        <input type='text' placeholder="Titre" name='Title' value="${selectedTitre}">
        <input type='text' placeholder="Auteur" name='Auteur' value="${selectedAuteur}">
        <select name="category" id="categorySelect" value="${selectedCategories}">
        ${catgs.innerHTML}
		</select>
        <input type='number' placeholder="Prix" name='Prix' value="${selectedPrix}">
        <input id="files" name="cover" type="file">
        <input type='number' placeholder="Nombre" name='count' value="${selectedStock}">
        <div id="bookButtons">
            <button onClick="modifyBook(${selectedId})" id="modify">Confirmer</button>
        </div>
    `
    catgs.value=selectedCategories
}

function modifyBook(selectedId){
    // let serialCodeEdit = document.querySelector("#bookDetails>input[name='serialCode']").value
    let TitleEdit = document.querySelector("#bookDetails>input[name='Title']").value
    let AuteurEdit = document.querySelector("#bookDetails>input[name='Auteur']").value
    let categoryEdit = document.querySelector("#bookDetails>select[name='category']").value
    let prixEdit = document.querySelector("#bookDetails>input[name='Prix']").value
    let coverEdit = document.querySelector("#bookDetails>input[name='cover']").files[0]
    let stockEdit = document.querySelector("#bookDetails>input[name='count']").value
    console.log(stockEdit+"   "+TitleEdit+"   "+AuteurEdit+"   "+categoryEdit+"   "+prixEdit)
    if(stockEdit=="" || TitleEdit=="" || AuteurEdit=="" || categoryEdit=="" || prixEdit==""){
        alert("Remplir tous les valeurs avant de confirmer!")
        return;
    }

    ajax.open("POST", link)
    ajax.onload = (e)=>{
        alert("Le livre a été modifié.")
        reloadTable(ajax.responseText)
        selectBook(currentSelection)
    }
    
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    if(coverEdit==null){
		ajax.send(`type=modify&stock=${stockEdit}&id=${selectedId}&Title=${TitleEdit}&Auteur=${AuteurEdit}&Categories=${categoryEdit}&Prix=${prixEdit}`)
	}else{
	    getBase64(coverAdd, function(base64String) {
			ajax.send(`type=modify&stock=${stockEdit}&id=${selectedId}&Title=${TitleEdit}&Auteur=${AuteurEdit}&Categories=${categoryEdit}&Prix=${prixEdit}&Cover=${base64String}`)
    	});
	}
}
