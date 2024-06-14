package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class ChartDisplay {

    public static void displayChart(List<info_walut> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (info_walut walut : data) {
            dataset.addValue(walut.getWartoscDb(), "Currency", walut.getWalutaDb());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Currency Rates",
                "Currency",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        chartPanel.setMouseWheelEnabled(true);

        JFrame frame = new JFrame("Currency Rates Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
