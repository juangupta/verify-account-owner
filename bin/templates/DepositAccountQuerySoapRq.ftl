<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns3:consultarDetalleExtendido xmlns:ns2="http://grupobancolombia.com/ents/SOI/MessageFormat/V2.1" xmlns:ns3="http://grupobancolombia.com/intf/Producto/Depositos/ConsultaCuentaDepositos/V1.0">
    <informacionCuenta>
        <tipoCuenta>"${body.getInformacionCuenta().getTipoCuenta()}"</tipoCuenta>
        <numeroCuenta>"${body.getInformacionCuenta().getNumeroCuenta()}"</numeroCuenta>
    </informacionCuenta>
    <identificacionCliente>
        <tipoDocumento>cc</tipoDocumento>
        <numeroDocumento>999888666777</numeroDocumento>
    </identificacionCliente>
</ns3:consultarDetalleExtendido>