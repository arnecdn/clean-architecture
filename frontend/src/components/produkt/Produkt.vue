<template>
  <div>
    <input v-model="produktTilLagring.beskrivelse" placeholder="Beskrivelse">
    <input v-model="produktTilLagring.pris" placeholder="Pris">
    <button v-if="produktTilLagring.id == ''" v-on:click.stop.prevent="opprettProdukt">Opprett produkt</button>
    <button v-if="produktTilLagring.id != ''" v-on:click.stop.prevent="lagreProdukt(produktTilLagring.id)">Lagre produkt</button>

    <p>{{ melding }}</p>
    <table>
      <tr>
        <td>Administrer</td>
        <td>Beskrivelse</td>
        <td>Pris</td>
      </tr>

      <tr v-for="produkt in produkter">
        <td>
          <button v-on:click="slettProdukt(produkt.id)">Slett</button>
          <button v-on:click="hentProdukt(produkt.id)">Endre</button>
        </td>
        <td>
          {{produkt.beskrivelse}}
        </td>
        <td>
          {{produkt.pris}}
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        knappTekst: 'Opprett produkt',
        produkter: [],
        produktTilLagring: {
          id: '',
          beskrivelse: '',
          pris: ''
        }
      }
    },
    methods: {
      tømFelter() {
        this.produktTilLagring.id = ''
        this.produktTilLagring.beskrivelse = '',
          this.produktTilLagring.pris = ''
      },
      lagreProdukt(id) {
        axios
          .put('/api/produkt/' + id,
            this.produktTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Produkt er oppdatert!'
            this.hentAlle()
            this.tømFelter()
            this.$router.push('/produkt')
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettProdukt() {

        axios
          .put('/api/produkt/', this.produktTilLagring,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Produkt er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettProdukt(id) {
        axios.delete('/api/produkt/' + id, {},
          {}
        ).then(response => {
          this.hentAlle()
        })
      },
      hentProdukt(id) {
        axios.get('/api/produkt/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.produktTilLagring = response.data))
      },
      hentAlle() {
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

      },
    },

    mounted() {
      console.log('hent alle')
      this.hentAlle()
    }
  }
</script>