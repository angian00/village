#!/bin/sh

filename=$1

awk -F, '
    BEGIN { max=0.0; }

    {
        if ($12 > max) {
            max = $12;
            max_rec = $0;
        }
    }

    END { print max_rec; }
' $1
