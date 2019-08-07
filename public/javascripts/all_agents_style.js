
// show student details
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
    $("#district").val("District :" + $.trim($district));
    $("#sector").val("Sector :" + $.trim($sector));
    $("#cell").val("Cell :" + $.trim($cell));
    $("#agromeration").val("Village :" + $.trim($village));
    $("#phonenumber").val("Phone number :" +$.trim($phone));
    $("#firstname").val("First name : " +$.trim( $firstname));
    $("#lastname").val("Last name : " + $.trim($lastname));
    $("#address").val("Address : " + $.trim($address));
    $("#nationality").val("Nationality : " + $.trim($nationality));
    $("#nid").val("National ID : " + $.trim($nid));
    $("#company").val("Company : " + $.trim($company));
    $("#sitename").val("Site name : " + $.trim($sitename));

     var photopath = $.trim($passportphoto);
     var path = photopath.replace("uploads\\", "uploads/")
    console.log(photopath.replace("uploads\\", "uploads/"));
     document.getElementById("passportphoto").setAttribute("src", "../assets/"+path );
});

  // When accept button is clicked
$(".accept").click(function() {

    $("#approve").modal('show');

    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text
    var userid = $("#userid").val($.trim($rowId));

    // var data = $("#approvalform").serialize();

    // $.ajax(
    //  {
    //
    //      type: "POST",
    //      url:"/applicant/approve",
    //      data: data,
    //      success:function(data) {
    //
    //      }
    //
    //  });


});


// When accept button is clicked
$(".reject").click(function() {

    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text
    var rejectid = $("#rejectid").val($.trim($rowId));

    $("#rejectmodel").modal('show');

});


    // When edit button is clicked
    $(".editbtn").click(function() {

        var $row = $(this).closest("tr");    // Find the row
        var $rowId = $row.find(".id").text(); // Find the text
        var $fullname = $row.find(".fullname").text(); // Find the text
        var $gender = $row.find(".gender").text(); // Find the text
        var $company = $row.find(".company").text(); // Find the text
        var $email = $row.find(".email").text(); // Find the text
        var $address = $row.find(".address").text(); // Find the text

         $("#userid").val($.trim($rowId));
         $("#fullname").val($.trim($fullname));
         $("#gender").val($.trim($gender));
         $("#company").val($.trim($company));
         $("#email").val($.trim($email));
         $("#address").val($.trim($address));
        $("#editmodel").modal('show');

    });



// When delete button is clicked
$(".deletebtn").click(function() {

    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text

    $("#deletid").val($.trim($rowId));
    $("#deletemodel").modal('show');

});


     $(".mylink").click(function () {
         var data = $("#link").val();
         localStorage.setItem("chatid",data )


     });


     $(".btnsub").click(function () {
       var chatid = localStorage.getItem("chatid")
         $("#chatid").val(chatid);

     });


