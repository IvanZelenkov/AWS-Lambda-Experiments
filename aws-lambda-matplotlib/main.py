import matplotlib.pyplot as plt

coldStarts = {
        "1st": 758, "2nd": 1137, "3rd": 1477,
        "4th": 854, "5th": 1832, "6th": 849,
        "7th": 1908, "8th": 786, "9th": 822,
        "10th": 896
}


warmStarts = {
        "1st": 16, "2nd": 18, "3rd": 17,
        "4th": 42, "5th": 16, "6th": 23,
        "7th": 18, "8th": 21, "9th": 29,
        "10th": 22
}

coldStartsOrderNumber = list(coldStarts.keys())
coldStartDuration = list(coldStarts.values())

warmStartsOrderNumber = list(warmStarts.keys())
warmStartDuration = list(warmStarts.values())

fig = plt.figure(figsize=(10, 5))

plt.subplot(1, 2, 1)
plt.bar(coldStartsOrderNumber, coldStartDuration, color='tab:blue', width=0.5)
plt.xlabel("Cold starts")
plt.ylabel("Duration (ms)")
plt.title("Comparison of cold start durations")

plt.subplot(1, 2, 2)
plt.bar(warmStartsOrderNumber, warmStartDuration, color='orange', width=0.5)
plt.xlabel("Warm starts")
plt.ylabel("Duration (ms)")
plt.title("Comparison of warm start durations")
plt.savefig('cold_and_warm_starts.png')