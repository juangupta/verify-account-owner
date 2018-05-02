<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns3:consultarInformacionExtendidaCuenta xmlns:ns2="http://grupobancolombia.com/ents/SOI/MessageFormat/V2.1" xmlns:ns3="http://grupobancolombia.com/intf/Producto/Depositos/ConsultaCuentaDepositos/V2.0">
    <informacionCuenta>
        <condicionesComerciales>
            <informacionTransaccion>
                <tipoCuenta>${body['ProductType']}</tipoCuenta>
                <numeroCuenta>${body['ProductNumber']}</numeroCuenta>
            </informacionTransaccion>
        </condicionesComerciales>
        <informacionCliente>
            <identificacionCliente>
                <tipoIdentificacion>${body['BeneficiaryDocumentType']}</tipoIdentificacion>
                <numeroIdentificacion>${body['BeneficiaryDocument']}</numeroIdentificacion>
            </identificacionCliente>
        </informacionCliente>
    </informacionCuenta>
</ns3:consultarInformacionExtendidaCuenta>