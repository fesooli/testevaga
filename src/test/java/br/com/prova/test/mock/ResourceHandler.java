package br.com.prova.test.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.core.io.Resource;

public interface ResourceHandler {

	public Resource saveResource(InputStream is, String context, String fileName)
            throws IOException;

    public void readResource(OutputStream os, String context, String fileName) throws IOException;

    public Resource getResource(String context, String fileName);

    public void deleteResource(String context, String fileName);
}
