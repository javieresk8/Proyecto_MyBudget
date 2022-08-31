package com.erazojavier.proyecto_mybudget.models

class Ingreso (
    var idUsuario: String,
    var nombreIngreso: String,
    var montoIngreso: String,
    var cuentaBancaria: String,
    var descripcion: String,
    var frecuencia: String
        ){
    constructor():this("","","","","", "")
}