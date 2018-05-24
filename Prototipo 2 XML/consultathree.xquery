let $data:= doc("chemical_data.xml")//root/row[chemical_data = "C6H7N"]
for $comp in doc("compounds.xml")//root/row
where $comp/id = $data/compound_id
return $comp
