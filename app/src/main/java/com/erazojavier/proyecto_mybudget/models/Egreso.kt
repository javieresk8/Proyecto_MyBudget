package com.erazojavier.proyecto_mybudget.models

class Egreso (
    var idUsuario: String,
    var nombreEgreso: String,
    var montoEgreso: String,
    var cuentaBancaria: String,
    var descripcion: String,
    var esPlanificado: Boolean,
    var frecuencia: String
        ) {
    constructor():this("","","","","", false, "")
}