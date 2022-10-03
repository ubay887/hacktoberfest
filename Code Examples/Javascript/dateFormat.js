/*
    Date Formatter with custom sperator
*/

// format date javascript
function dateFormater(date, separator) {
    var day = date.getDate();
    // add +1 to month because getMonth() returns month from 0 to 11
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    // show date and month in two digits
    // if month is less than 10, add a 0 before it
    if (day < 10) {
    day = '0' + day;
    }
    if (month < 10) {
    month = '0' + month;
    }

    // now we have day, month and year
    // use the separator to join them
    return day + separator + month + separator + year;
}

// get current date
var now = new Date();
console.log(dateFormater(now, '/')); // output: 01/01/2021
console.log(dateFormater(now, '-')); // output: 01-01-2021
console.log(dateFormater(now, '.')); // output: 01.01.2021