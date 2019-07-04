
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



// Agent signup form
$('#agentsigupform').validate({
     rules : {
         names : {
            required : true,
             minlength : 6
         },
         gender : {
            required : true
        } ,
         email : {
            required : true,
             email: true
        },
        password : {
            required : true,
            minlength : 8        }


    }
});

$('#agentsigninform').validate({

    rules : {
        email : {
            required : true,
            email : true
        },
        password : {
            required : true,
        }

    }
});



