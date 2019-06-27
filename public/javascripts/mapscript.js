
  $(document).ready(function () {
    $("#datetimepicker8").datepicker({
        icons: {
             time: "fa fa-clock-o",
             date: "fa fa-calendar",
             up: "fa fa-arrow-up",
             down: "fa fa-arrow-down"

        }
    });

  });


<!-- Javascript autocomplete function -->
$(function(name) {
    var availableTutorials  =  [
    value=  "/auto/comp?name="+name


    ];
    $( "#automplete-1" ).autocomplete({
        minLength:2,
        source: availableTutorials
    });
    });


//  Get a sum of sum of html table data values


//  $('.totalColumn').each(function() {
//      var thisId = $(this).find('.sumofamount').text();
//
//      var $rowsToGroup = $(this).nextAll('tr').filter(function() {
//          return $(this).find('.sumofamount').text() === thisId;
//      });
//
//      $rowsToGroup.each(function() {
//          $(this).remove();
//
//      });
//
//  });


  // Remove duplicates rows inside for loop table

  $('.totalColumn').each(function() {
      [].forEach.call(document.querySelectorAll('.totalColumn'),
          (row, i) => row.parentNode[(i? 'remove' : 'append') + 'Child'](row)
      );
  });

$('.totalColumnService').each(function() {
      [].forEach.call(document.querySelectorAll('.totalColumnService'),
          (row, i) => row.parentNode[(i? 'remove' : 'append') + 'Child'](row)
      );
  });


// Button useed to print fuel History
  $('#print_button').on('click', function() {
//      document.title = 'Fuel report';

      // print hide and show div on fuel history pages
      $("#hiddendiv_whenprint").hide();
      $("#hiddendiv_whenprint2").hide();
      $("#printBtn").hide();
      $("#hiddenFooter").hide();
      $("#fideformFuelAlldrivers").hide();
      $("#hiddenTitle").hide();
      $("#fuelreportTitle").show();

    // print hide and show div on Services history pages

      $("#hideHead").hide();
      $("#hideFormServices").hide();
      $("#hide_title").hide();
      $("#hidefooter").hide();
      $("#fuelreportTitle").show();


      print();

  });


  $(function() {
      $("#date-picker").datepicker({
          icons: {
              time: "fa fa-clock-o",
              date: "fa fa-calendar",
              up: "fa fa-arrow-up",
              down: "fa fa-arrow-down"
           }
//          format: " MM/yyyy/dd"    // Notice the Extra space at the beginning
////          viewMode: "years",
//          viewMode: "month",
//
//          minViewMode: "years",
//          minViewMode: "month",
//
////
//          endDate: new Date()

      });
  });





