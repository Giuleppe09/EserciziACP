import matplotlib.pyplot as plt #per creare il grafico
import numpy as np #per generare dati numerici

fig = plt.figure() #crea una nuova figura
ax = plt.axes() #restituisce gli assi della figura

# np.linspace(0, 10, 1000) genera 1000 punti equidistanti 
# nell'intervallo da 0 a 10 e li assegna alla variabile x.
x = np.linspace(0, 10, 1000)

ax.set_title('Simple Plot') # Add a title
ax.set_xlabel('x label') # Add x label
ax.set_ylabel('y label'); # Add y label

ax.set_xlim(-5, 15)
ax.set_ylim(-3, 3)


fig, axs = plt.subplots(2, 2, figsize=(10, 6)) #figura con una griglia di 2x2 grid of Axes
x = np.linspace (0,10,1000)

axs[0,0].plot(x,np.sin(x))
axs[0,1].plot(x,np.cos(x),color='maroon')
axs[1,0].plot(x,np.sin(x+1),color = 'blue')
axs[1,1].plot(x,np.cos(x+1),color='green')



ax.legend() #aggiunta della legenda, basandosi sulle etichette fornite durante la creazione di curve.