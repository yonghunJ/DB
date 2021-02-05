

let $item := doc("purchaseorders.xml")//PurchaseOrders/PurchaseOrder/item
let $partid_values := sort(distinct-values($item/partid/text()))

for $partid_value in $partid_values
return
<totalcost partid="{$partid_value}">
{
sum($item[partid=$partid_value]/quantity) * distinct-values($item[partid=$partid_value]/price)
}
</totalcost>