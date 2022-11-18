import matplotlib.pyplot as plt

coldStarts = {
        "1st": 13574, "2nd": 13218, "3rd": 11031,
        "4th": 13397, "5th": 13434, "6th": 13081,
        "7th": 13600, "8th": 14224, "9th": 13700,
        "10th": 11118
}


warmStarts = {
        "1st": 408, "2nd": 354, "3rd": 529,
        "4th": 506, "5th": 209, "6th": 188,
        "7th": 154, "8th": 218, "9th": 432,
        "10th": 160
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