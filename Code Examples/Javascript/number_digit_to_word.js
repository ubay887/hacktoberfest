/*
This program will convert a number in word format
Input: 199
Output : one hundred and ninety nine

Constraint : 
    0 <= num <= 999
*/

function show_number_in_words(num) {
    let num_in_words = "";
    let ones = [
      "zero",
      "one",
      "two",
      "three",
      "four",
      "five",
      "six",
      "seven",
      "eight",
      "nine",
    ];
    let tens = [
      "ten",
      "eleven",
      "twelve",
      "thirteen",
      "fourteen",
      "fifteen",
      "sixteen",
      "seventeen",
      "eighteen",
      "nineteen",
    ];
    let tens2 = [
      "twenty",
      "thirty",
      "forty",
      "fifty",
      "sixty",
      "seventy",
      "eighty",
      "ninety",
    ];
    let hundreds = [
      "one hundred",
      "two hundred",
      "three hundred",
      "four hundred",
      "five hundred",
      "six hundred",
      "seven hundred",
      "eight hundred",
      "nine hundred",
    ];
    if (num < 10) {
      num_in_words = ones[num];
    } else if (num < 20) {
      num_in_words = tens[num - 10];
    } else if (num < 100) {
      num_in_words = tens2[Math.floor(num / 10) - 2];
      if (num % 10 != 0) {
        num_in_words += " " + ones[num % 10];
      }
    } else if (num < 1000) {
      num_in_words = hundreds[Math.floor(num / 100) - 1];
      if (num % 100 != 0) {
        num_in_words += " and ";
        if (num % 100 < 10) {
          num_in_words += ones[num % 100];
        } else if (num % 100 < 20) {
          num_in_words += tens[(num % 100) - 10];
        } else {
          num_in_words += tens2[Math.floor((num % 100) / 10) - 2];
          if (num % 10 != 0) {
            num_in_words += " " + ones[num % 10];
          }
        }
      }
    } else {
      console.log("Number is out of range");
    }
    console.log("You Entered : " + num_in_words);
    return true;
  }

//Driver Code
show_number_in_words(99)