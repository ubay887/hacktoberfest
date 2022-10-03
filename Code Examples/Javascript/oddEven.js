//Ganjil Genap
let genap = [];
let ganjil = [];

function pisah(max) {
    for (let i = 0; i < max; i++) {
        if (i % 2 == 0) {
            genap.push(i)

        } else {
            ganjil.push(i)

        }
    }
}

pisah(100);

console.log("Genap", genap);
console.log("Ganjil", ganjil);
