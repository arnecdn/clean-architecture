<template>
  <div>
    <select v-model="produkter">
      <option v-for="p in produkter" :key="p.id" :value="p.id">{{ p.beskrivelse }}</option>
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

        selgere: [],
        kunder: [],
        produkter: [],
        melding: "",
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
        return axios.get('/api/salg', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        )
      },
      hentAlleProdukter() {
        return axios.get('/api/produkt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        )
      },
      hentAlt(){
        axios.all(this.hentAlleSalg(), this.hentAlleProdukter())
          .then(axios.spread((salgsRespons, produktRespons ) => {
            this.salgsListe = salgsRespons.data
            this.produkter = produktRespons.data

        }));
      }

    },

    mounted() {
      console.log('hent alt under montering av template')
      this.hentAlt()
      //this.hentAlleProdukter()

      console.log('hent alle salg' + this.salgsListe)
      console.log('hent alle produkter' + this.produkter);
    }
  }
</script>