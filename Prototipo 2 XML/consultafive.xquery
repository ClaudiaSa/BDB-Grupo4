let $struc:= doc("structures.xml")//root/row[dimension != "1D"]
for $comp in doc("compounds.xml")//root/row
where $comp/parent_id = $struc/compound_id
return $comp
