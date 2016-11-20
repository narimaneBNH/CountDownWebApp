<html>
<body style="background-color:powderblue;">
<h2>Veuillez choisir une capitale pour avoir l'heure qu'il est </h2>
<!--
<% int z=1; %>
-->

<form method="Post" action="countdown">
	<!--
	
	<input type="text" name="city"" value= "Paris"/> 
	-->

	<SELECT name="city"" size="1">
	<OPTION selected>Paris
	<OPTION>Algiers
	<OPTION>Cairo
	<OPTION>Rome
	<OPTION>Riyadh
	<OPTION>Tokyo
	<OPTION>Amsterdam
	<OPTION>New York
	<OPTION>Berlin
	<OPTION>Caracas
	<OPTION>Hong Kong

	</SELECT>
	<!--
        <input type="hidden" name="product_no" value="<%=z%>" />
	-->
        <input type='submit' value="Valider" />


</form>

</body>
</html>
