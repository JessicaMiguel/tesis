package com.example.tesis.process;

import com.example.tesis.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Locale;

@ComponentScan(basePackages = {"com.example.tesis.process"})
@Component
public class BeginProcess {

    @Autowired(required = false)
    private FileProcess fileProcess;
    @Autowired
    private FileProcessCobranzasImpl fileProcessCobranzas;
    @Autowired
    private FileCuentaCorrienteImpl fileCuentaCorriente;

    /**
     * metodo que se encarga de empezar el proceso de carga del archivo original.
     * Se llama a un metodo que se encarga de cargar el archivo en la base de datos, todos los datos
     * del archivo a "wacha pelada"
     * Por otro lado, se devuelve un resultado del proceso donde se expresa que el proceso de carga
     * del archivo se realiza en segundo plano, mientras se puede seguir utilizando el programa.
     * @param file Archivo que contiene los datos originales
     * @return resultProcess, contiene status, el idProcess, errores y mensages.
     */
    public ResultProcess massiveProcess (File file){

        String nombre = file.getName();
        ResultProcess resultProcess = new ResultProcess();
        //buscar como crear el idProcess, que se va a corresponder con el proceso de carga del archivo.

        if (nombre.toUpperCase(Locale.ROOT).contains("COBRANZAS")){
            this.fileProcessCobranzas.readMassiveFile(file);
        }
        if (nombre.toUpperCase(Locale.ROOT).contains("CORRIENTE")){
            this.fileProcessCobranzas.readMassiveFile(file);
        }

        if (nombre.toUpperCase(Locale.ROOT).contains("CHEQUE")){
            this.fileProcessCobranzas.readMassiveFile(file);
        }

        if (nombre.toUpperCase(Locale.ROOT).contains("FACTURACION")){
            this.fileProcessCobranzas.readMassiveFile(file);
        }

        //boolean result = fileProcess.insertNewFile();

        return resultProcess;
    }
}