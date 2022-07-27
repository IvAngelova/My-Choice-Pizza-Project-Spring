const ordersCtnr = document.getElementById('ordersCtnr')
const allOrders = []

fetch(`http://localhost:8080/admin/api/orders`)
    .then(response => response.json())
    .then(data => {
        for (let order of data) {
            allOrders.push(order)
        }
        displayOrders(allOrders)
    })


const displayOrders = (orders) => {
    ordersCtnr.innerHTML = orders
        .map((o) => {

            // return `<tr>
            //             <th scope="row">1</th>
            //                    <td class="col-3">
            //                        <ol>
            //                            <li>
            //                               <div>ГОЛЯМА пица</div>
            //                                 <div> Основа: ДОМАТЕНА</div>
            //                                 <div>Съставки:
            //                                     <ul>
            //                                        <li>царевица</li>
            //                                         <li>маслини</li>
            //                                         <li>шунка</li>
            //                                         <li>прошуто</li>
            //                                         <li>моцарела</li>
            //                                         <li>пармезан</li>
            //                                     </ul>
            //                                 </div>
            //                                 <div>количество: 1</div>
            //                             </li>
            //                         </ol>
            //                     </td>
            //                     <td>${o.total} лв.</td>
            //                     <td>${o.created}</td>
            //                     <td>${o.delivery}</td>
            //                     <td class="col-3">
            //                         <p>потребителско име: ${o.user.username}</p>
            //                         <p>телефон: ${o.user.phoneNumber} </p>
            //                         <p>адрес: ${o.user.address}</p>
            //                     </td>
            //                     <td><a class="btn" th:href="@{/}">Готова</a></td>
            //            </tr>`

            return asOrder(o)

        })
        .join('')
}

function asOrder(o) {
    let orderHtml = `<tr>
                               <td class="col-3">
                                   <ol>`

    o.pizzas.forEach(pizza => {
            orderHtml += `<li>
            <div>${pizza.size} пица</div>
            <div> Основа: ${pizza.base}</div>
            <div>Съставки:</div>
                <ul>`

            pizza.ingredients.forEach(ingredient => {
                orderHtml += `<li>${ingredient}</li>`
            })

            orderHtml += `</ul>
            <div>количество: ${pizza.quantity}</div>
                          </li>`
        }
    )

    orderHtml += `</ol>
</td>
    <td>${o.total} лв.</td>
    <td>${o.created} ч.</td>
    <td>${o.delivery}</td>
    <td class="col-3">
        <p>потребителско име: ${o.user.username}</p>
        <p>телефон: ${o.user.phoneNumber} </p>
        <p>адрес: ${o.user.address}</p>
    </td>
    <td><a class="btn" th:href="@{/}">Готова</a></td>
</tr>`

    return orderHtml
}
