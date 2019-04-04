<template>
  <div>
    <select v-model="produkter">
      <option v-for="produkt in produkter" :key="produkt.id" :value="produkt.id">{{ produkt.beskrivelse }}</option>
    </select>

    <input v-model="salgTilLagring.produkt" placeholder="Produkt">
    <input v-model="salgTilLagring.antall" placeholder="antall">
    <input v-model="salgTilLagring.selger" placeholder="selger">
    <input v-model="salgTilLagring.kunde" placeholder="kunde">

    <button v-if="salgTilLagring.id == ''" v-on:click.stop.prevent="opprettSalg">Opprett salg</button>
    <button v-if="salgTilLagring.id != ''" v-on:click.stop.prevent="lagreSalg(salgTilLagring.id)">Lagre salg</button>

    <p>{{ melding }}</p>
    <table>
      <tr>
        <td>Administrer</td>
        <td>Produkt</td>
        <td>Selger navn</td>
        <td>Kunde navn</td>
        <td>Pris</td>
      </tr>
      <tr v-for="salg in salgsListe">
        <td>
          <button v-on:click="slettSalg(salg.id)">Slett</button>
          <button v-on:click="hentSalg(salg.id)">Endre</button>
        </td>
        <td>{{salg.produkt.beskrivelse}}</td>
        <td>{{salg.selger.navn}}</td>
        <td>{{salg.kunde.navn}}</td>
        <td>{{salg.pris}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        produkter: [],
        selgere: [],
        kunder: [],
        melding: "",
        knappTekst: 'Opprett salg',
        salgsListe: [],
        salgTilLagring: {
          id: '',
          produkt: '',
          antall: '',
          selger: '',
          kunde: ''
        }
      }
    },
    methods: {
      tømFelter() {
        this.salgTilLagring.id = ''
        this.salgTilLagring.antall = '',
          this.salgTilLagring.kunde = '',
          this.salgTilLagring.selger = '',
          this.salgTilLagring.produkt.id = ''
      },
      lagreSalg(id) {
        axios
          .put('/api/salg/' + id,
            this.salgTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Salg er oppdatert!'
            this.hentAlleSalg()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettSalg() {

        axios
          .put('/api/salg/', this.salgTilLagring,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Salg er oppdatert!'
            this.hentAlleSalg()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettSalg(id) {
        axios.delete('/api/salg/' + id, {},
          {}
        ).then(response => {
          this.hentAlleSalg()
        })
      },
      hentSalg(id) {
        axios.get('/api/salg/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.salgTilLagring = response.data))
      },
      hentAlleSalg() {
        axios.get('/api/salg', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.salgsListe = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      },
      hentAlleProdukter() {
        axios.get('/api/produkt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.produkter = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      }

    },

    mounted() {
      console.log('hent alle')
      this.hentAlleSalg()
      this.hentAlleProdukter()
    }
  }
</script>