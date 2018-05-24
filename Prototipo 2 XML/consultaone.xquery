for $x in doc("compounds.xml")/root/row
where $x/parent_id = "27732"
return $x