package com.example.payments;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** Builds a report with a text block (Java 15+) and Stream.toList() (Java 16+). */
public class SettlementReport {

    public record Line(String channel, Money amount, Money fee, PaymentStatus status) {}

    private final FeeCalculator fees = new FeeCalculator();

    public List<Line> lines(List<Payment> payments, Money limit) {
        return payments.stream()
                .map(p -> new Line(fees.channel(p), p.amount(), fees.fee(p), PaymentStatus.decide(p, limit)))
                .toList();
    }

    public String render(List<Payment> payments, Money limit) {
        var lines = lines(payments, limit);
        Map<String, Long> byChannel = lines.stream()
                .collect(Collectors.groupingBy(Line::channel, TreeMap::new, Collectors.counting()));
        var declined = lines.stream().filter(l -> l.status() == PaymentStatus.DECLINED).count();

        return """
                Settlement report
                -----------------
                Payments : %d
                Declined : %d
                Channels : %s
                """.formatted(lines.size(), declined, byChannel);
    }
}
