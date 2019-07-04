
// Register cell form

$('#cellform').validate({
    rules : {
        sector : {
            required : true,
        },
        cell :{
            required : true,
         },

       description :{
            required : true,
         },

    }
});


// Register Sector
$('#sectorform').validate({
    rules : {
        district : {
            required : true,
        },
        stector :{
            required : true,
         },

       description :{
            required : true,
         },

    }
});




// Register district

$('#districtform').validate({
    rules : {
        province : {
            required : true,
        },
        district :{
            required : true,
         },

       description :{
            required : true,
         },

    }
});


// Register district

$('#provinceform').validate({
    rules : {
        province : {
            required : true,
        },
        description :{
            required : true,
        },

    }
});







$('#login-form').validate({
     rules : {
        user_name : {
            required : true

        },
         password : {
            required : true
        }


    }
});



$('#DriverRegistration-form').validate({

    rules : {
        names : {
            required : true,
            minlength : 6
        },

        age :{
            greaterthan:20,
            required : true,
            minlenght :2,
            maxlenght : 2
        },

        phone_number :{
            required : true,
            minlsize: 10,
            maxlenght : 13
        },


        address : {
            required : true,
            minlength : 3
        },

        plate_number : {
            required : true,
            minlength : 3

        },

        username : {
            required : true,
            minlength : 3

        } ,

        vehicle_plate_number : {
            required : true,
            minlength : 3

        } ,
        password : {
            required : true,
            minlength : 5

        }

    }
});




//
//
//function FormValidation(){
//    //First Name Validation
//    var fn=document.getElementById('InputAge').value;
//    if(fn == ""){
////        alert('Please Enter age');
//        $("#InputAge").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please Enter age</div>");
//
//        document.getElementById('InputAge').style.borderColor = "red";
//        return false;
//    }
//    else if(( fn < 24) && ( fn > 45)){
//        $("#InputAge").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please Enter age greater than 24 and less than 45</div>");
//        document.getElementById('InputAge').style.borderColor = "red";
//        return false;
//    }
//    else{
//        document.getElementById('InputAge').style.borderColor = "green";
//    }
//    if (/^[0-9]+$/.test(document.getElementById("firstname").value)) {
//        alert("First Name Contains Numbers!");
//        document.getElementById('firstname').style.borderColor = "red";
//        return false;
//    }else{
//        document.getElementById('firstname').style.borderColor = "green";
//    }
//    if(fn.length <=2){
//        alert('Your Name is To Short');
//        document.getElementById('firstname').style.borderColor = "red";
//        return false;
//    }else{
//        document.getElementById('firstname').style.borderColor = "green";
//    }
//}