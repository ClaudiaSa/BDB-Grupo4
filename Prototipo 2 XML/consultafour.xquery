for $struc in doc("structures.xml")//root/row
where $struc/compound_id = "34565"
return $struc
