package com.ilozanof.learning.kafka.serialization.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author i.fernandez@nchain.com
 * Distributed under the Open BSV software license, see the accompanying file LICENSE.
 * @date 2020-04-23 11:31
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Record {
    private Integer id;
    private String title;
}
