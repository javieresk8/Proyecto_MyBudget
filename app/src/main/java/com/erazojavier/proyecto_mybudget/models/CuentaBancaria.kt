package com.erazojavier.proyecto_mybudget.models

class CuentaBancaria(
    var idUsuario: String,
    var nombreBanco: String,
    var montoInicial: String,
    var numeroCuenta: String,
    var tipoCuenta: String
) {
    constructor():this("","","","","")

}