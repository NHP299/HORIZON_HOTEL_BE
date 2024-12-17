package com.horizon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportUserResponse {
    private long totalAccounts;
    private long newAccountsToday;
    private long activatedAccounts;
    private double activatedPercentage;
}
