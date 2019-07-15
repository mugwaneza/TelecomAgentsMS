
$(".detail").click(function() {
    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text


    var $district = $row.find(".district").text(); // Find the text
    var $sector = $row.find(".sector").text(); // Find the text
    var $cell = $row.find(".cell").text(); // Find the text
    var $firstname = $row.find(".firstname").text(); // Find the text
    var $lastname = $row.find(".lastname").text(); // Find the text
    var $phonenumber = $row.find(".phonenumber").text(); // Find the text
    var $village = $row.find(".agromeration").text(); // Find the text
    var $phone = $row.find(".phonenumber").text(); // Find the text
    var $address = $row.find(".address").text(); // Find the text
    var $nationality = $row.find(".nationality").text(); // Find the text
    var $nid = $row.find(".nid").text(); // Find the text
    var $company = $row.find(".company").text(); // Find the text
    var $sitename = $row.find(".sitename").text(); // Find the text
    var $passportphoto = $row.find(".passportphoto").text(); // Find the text




    // Append variables to the model dialog
    $("#district").val("District :" + $district);
    $("#sector").val("Sector :" + $sector);
    $("#cell").val("Cell :" + $cell);
    $("#agromeration").val("Village :" + $village);
    $("#phonenumber").val("Phone number :" + $phone);
    $("#firstname").val("First name : " + $firstname);
    $("#lastname").val("Last name : " + $lastname);
    $("#address").val("Address : " + $address);
    $("#nationality").val("Nationality : " + $nationality);
    $("#nid").val("National ID : " + $nid);
    $("#company").val("Company : " + $company);
    $("#sitename").val("Site name : " + $sitename);


     $("#passportphoto").attr('src', "");
    // $("#passportphoto").attr('src', "@routes.Assets.at(\"$passportphoto\") ");

});




