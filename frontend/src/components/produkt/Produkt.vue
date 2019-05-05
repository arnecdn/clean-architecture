<template>

  <!-- Menu Container -->
  <div>
    <div class="w3-container" id="menu">
      <div class="w3-content" style="max-width:700px">

        <h5 class="w3-center w3-padding-48"><span class="w3-tag w3-wide">Behandling av meny</span></h5>
        <div class="w3-container w3-padding-48 w3-card">

          <input v-model="produktTilLagring.beskrivelse" placeholder="Beskrivelse">
          <input v-model="produktTilLagring.pris" placeholder="Pris">
          <button v-if="produktTilLagring.id == ''" v-on:click.stop.prevent="opprettProdukt">Opprett produkt</button>
          <button v-if="produktTilLagring.id != ''" v-on:click.stop.prevent="lagreProdukt(produktTilLagring.id)">Lagre produkt</button>
          <button v-if="produktTilLagring.id != ''" v-on:click.stop.prevent="slettProdukt(produktTilLagring.id)">Slett</button>
          <button v-if="produktTilLagring.id != ''" v-on:click.stop.prevent="tømFelter()">Tøm felter</button>
          <p>{{ melding }}</p>
        </div>
        <div class="w3-container w3-padding-48 w3-card">
          <div v-for="produkt in produkter">
            <a class="w3-button w3-block w3-white" v-on:click="hentProdukt(produkt.id)">

              <div>
                <h5>Produkt: {{produkt.beskrivelse}}</h5>
                <p class="w3-text-grey">Pris: {{produkt.pris}}</p><br>

              </div>
            </a>

          </div>
        </div>

        <!--img src="/w3images/coffeehouse2.jpg" style="width:100%;max-width:1000px;margin-top:32px;"-->
      </div>
    </div>
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
          this.tømFelter()
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