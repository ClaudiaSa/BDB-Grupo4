let $names:= doc("names.xml")//root/row[name = "Cyanure"]
for $comp in doc("compounds.xml")//root/row
where $comp/id = $names/compound_id
return $comp
