
$(document).ready(function () {
 $(".linkMinus").on("click", function (evt) {
  evt.preventDefault();
  decreaseQuantity($(this));
 });

 $(".linkPlus").on("click", function (evt) {
  evt.preventDefault();
  increaseQuantity($(this));
 });

 $(".linkRemove").on("click", function (evt) {
  evt.preventDefault();
  removeProduct($(this));
 });
});

function decreaseQuantity(link) {
 itemId = link.attr("iid");
 quantityInput = $("#quantity" + itemId);
 newQuantity = parseInt(quantityInput.val()) - 1;

 if (newQuantity > 0) {
  quantityInput.val(newQuantity);
  updateQuantity(itemId, newQuantity);
 } else {
  showWarningModal('Minimum quantity is 1');
 }
}

function increaseQuantity(link) {
 itemId = link.attr("iid");
 quantityInput = $("#quantity" + itemId);
 newQuantity = parseInt(quantityInput.val()) + 1;

 if (newQuantity <= 10) {
  quantityInput.val(newQuantity);
  updateQuantity(itemId, newQuantity);
 } else {
  showWarningModal('Maximum quantity is 10');
 }
}

function updateQuantity(itemId, quantity) {
 url = contextPath + "cart/update/" + itemId + "/" + quantity;


 $.ajax({
  type: "POST",
  url: url,
  beforeSend: function (xhr) {
   xhr.setRequestHeader(csrfHeaderName, csrfValue);
  }

 }).done(function (updatedSubtotal) {
  updateSubtotal(updatedSubtotal, itemId);
  updateTotal();


 }).fail(function () {
  showErrorModal("Error while updating product quantity.");
 });
}

function updateSubtotal(updatedSubtotal, itemId) {
 $("#subtotal" + itemId).text(updatedSubtotal);
}


function updateTotal() {
 total = 0.0;


 $(".subtotal").each(function (index, element) {
  total += parseFloat(element.innerHTML);
 });

 $("#total").text($.number(total, 2) + " лв.");
 $("#endPrice").text($.number(total, 2) + " лв.");
 if ($('#delivery').val() === "С_ДОСТАВКА" && total > 0) {
  $("#endPrice").text($.number(total + 1, 2) + " лв.");
 }
}

$('#delivery').change(function() {
 delivery = $(this).val();
 console.log("delivery method selected: " + delivery);
 if (delivery === "С_ДОСТАВКА") {
  var endPriceText = $("#endPrice").text();
  console.log("endPriceText: " + endPriceText);
  var endPriceVal = endPriceText.substring(0, endPriceText.indexOf(" "));
  console.log("endPriceVal: " + endPriceVal);
  console.log("parseFloat: " + parseFloat(endPriceVal));
  var endPrice = parseFloat(endPriceVal.replace(",", ".")) + 1;
  console.log("endPrice: " + endPrice);
  $("#endPrice").text($.number(endPrice, 2) + " лв.");
 } else {
  updateTotal();
 }
});

function removeProduct(link) {
 url = link.attr("href");

 $.ajax({
  type: "DELETE",
  url: url,
  beforeSend: function(xhr) {
   xhr.setRequestHeader(csrfHeaderName, csrfValue);
  }
 }).done(function(response) {
  rowNumber = link.attr("rowNumber");
  removeProductHTML(rowNumber);
  updateTotal();

  window.location.reload();
  //showModalDialog("Cart", response);


 }).fail(function() {
  showErrorModal("Error while removing item.");
 });
}

function removeProductHTML(rowNumber) {
 $("#row" + rowNumber).remove();
 $("#blankLine" + rowNumber).remove();
}


