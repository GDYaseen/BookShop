let startDate = document.querySelector("input[name='startDate']")
let endDate = document.querySelector("input[name='endDate']")
let serialCode = document.querySelector("input[name='serialCode']")
let Title = document.querySelector("input[name='Title']")
let Auteur = document.querySelector("input[name='Auteur']")
let category = document.querySelector("select[name='category']")

let ajax = new XMLHttpRequest()
let link = window.location.pathname

function search(){
    let req = []
    if(startDate.value!=""){ req.push("DATE_FORMAT(date_vente,\"#Y-#m-#d\")>DATE_FORMAT('"+startDate.value+"',\"#Y-#m-#d\")")}
    if(endDate.value!=""){ req.push("DATE_FORMAT(date_vente,\"#Y-#m-#d\")<DATE_FORMAT('"+endDate.value+"',\"#Y-#m-#d\")")}
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
    ajax.send(`req=${s}`)
}
function reloadTable(data){
    let totalBenefice=0
    let tbody =`<tr><th>Code</th><th>Titre</th><th>Auteur</th><th>Category</th><th>Date de vente</th><th>Prix</th><th>Nombre Vendus</th><th>Total</th></tr>`
    let s = data.substring(1)
    
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
        
        let table = document.querySelector("#InventoryTable>table")
        
    for(let o of list){
        totalBenefice+=o["Total"]
        tbody+=`
            <tr>
                <td>${o["Id"]}</td>
                <td>${o["Titre"]}</td>
                <td>${o["Auteur"]}</td>
                <td>${o["Categories"]}</td>
                <td>${o["Date_vente"]}</td>
                <td>${o["Prix"]}</td>
                <td>${o["Sold"]}</td>
                <td>${o["Sold"]*o["Prix"]}</td>
            </tr>
        `
    }
    table.innerHTML=tbody
    document.querySelector("#InventoryTable>h1").innerHTML="Bénéfice: "+totalBenefice+" DH"
}
search()