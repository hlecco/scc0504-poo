import pandas as pd
import plotly.express as px

df = pd.read_csv("runs.csv")
df['operation, multithreaded'] = df['operation'] + ',' + df['multithreaded']
fig = px.line(df, x='size', y='time', color='operation, multithreaded')
fig.show()
