package com.stage.util;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.stage.model.Stage;

public final class PdfReportGenerator {

    private PdfReportGenerator() {
    }

    public static byte[] generateStageReport(Stage stage, DateFormat dateFormat) {
        StringBuilder content = new StringBuilder();
        List<String> lines = new ArrayList<>();
        lines.add("Rapport de stage PFE");
        lines.add("Sujet : " + safe(stage.getSujet()));
        lines.add("Annee universitaire : " + safe(stage.getAnneeUniversitaire()));
        lines.add("Periode : " + formatDate(stage.getDate_debut(), dateFormat) + " -> "
                + formatDate(stage.getDate_fin(), dateFormat));
        lines.add("Etudiant : " + safe(stage.getEtudiant().getNom()) + " " + safe(stage.getEtudiant().getPrenom()));
        lines.add("Filiere : " + safe(stage.getEtudiant().getFiliere().getIntitule()));
        lines.add("Entreprise : " + safe(stage.getEntreprise().getNom()));
        lines.add("Encadrant academique : " + safe(stage.getEncadrementAcademique().getNomEncadrantAcademique()));
        lines.add("Encadrant professionnel : " + safe(stage.getEncadrantEntreprise().getNom()));
        lines.add("Description : " + safe(stage.getDescription()));
        lines.add("Objectifs : " + safe(stage.getObjectifs()));
        lines.add("Solution : " + safe(stage.getSolution()));
        lines.add("Demarche : " + safe(stage.getDemarche()));
        lines.add("Outils : " + safe(stage.getOutils()));
        lines.add("Environnement : " + safe(stage.getEnvironnement()));

        int y = 780;
        for (String line : lines) {
            content.append("BT /F1 11 Tf 40 ").append(y).append(" Td (")
                    .append(escape(line))
                    .append(") Tj ET\n");
            y -= 22;
        }

        String stream = content.toString();
        List<Integer> offsets = new ArrayList<>();
        StringBuilder pdf = new StringBuilder();
        pdf.append("%PDF-1.4\n");

        offsets.add(pdf.length());
        pdf.append("1 0 obj << /Type /Catalog /Pages 2 0 R >> endobj\n");

        offsets.add(pdf.length());
        pdf.append("2 0 obj << /Type /Pages /Count 1 /Kids [3 0 R] >> endobj\n");

        offsets.add(pdf.length());
        pdf.append("3 0 obj << /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >> endobj\n");

        byte[] streamBytes = stream.getBytes(StandardCharsets.UTF_8);
        offsets.add(pdf.length());
        pdf.append("4 0 obj << /Length ").append(streamBytes.length).append(" >> stream\n");
        pdf.append(stream);
        pdf.append("endstream endobj\n");

        offsets.add(pdf.length());
        pdf.append("5 0 obj << /Type /Font /Subtype /Type1 /BaseFont /Helvetica >> endobj\n");

        int xrefStart = pdf.length();
        pdf.append("xref\n0 6\n");
        pdf.append("0000000000 65535 f \n");
        for (Integer offset : offsets) {
            pdf.append(String.format("%010d 00000 n %n", offset));
        }
        pdf.append("trailer << /Size 6 /Root 1 0 R >>\n");
        pdf.append("startxref\n").append(xrefStart).append("\n%%EOF");
        return pdf.toString().getBytes(StandardCharsets.UTF_8);
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }

    private static String formatDate(java.util.Date date, DateFormat dateFormat) {
        return date == null ? "" : dateFormat.format(date);
    }

    private static String escape(String value) {
        return value.replace("\\", "\\\\").replace("(", "\\(").replace(")", "\\)");
    }
}
