package com.demo.htmlToPdf.util;

import java.nio.file.Path;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class PdfMergeUtil {

    private PdfMergeUtil() {
    }

    public static void merge(List<Path> sources, Path target) throws Exception {

	PDFMergerUtility merger = new PDFMergerUtility();

	for (Path src : sources) {
	    merger.addSource(src.toFile());
	}

	merger.setDestinationFileName(target.toString());
	merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
    }

}
